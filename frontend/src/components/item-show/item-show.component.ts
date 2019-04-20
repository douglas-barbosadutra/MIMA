import { ItemDTO } from 'src/dto/ItemDTO';
import { ItemService } from 'src/services/item.service';
import { Router } from "@angular/router";
import { WBSDTO } from 'src/dto/WBSDTO';
import {FlatTreeControl} from '@angular/cdk/tree';
import {Component, Injectable, OnInit} from '@angular/core';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
import {BehaviorSubject, Observable} from 'rxjs';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

export class LoadmoreNode {
  childrenChange = new BehaviorSubject<LoadmoreNode[]>([]);

  get children(): LoadmoreNode[] {
    return this.childrenChange.value;
  }

  constructor(public item: ItemDTO,
              public hasChildren = false,
              public loadMoreParentItem: ItemDTO | null = null) {}
}

/** Flat node with expandable and level information */
export class LoadmoreFlatNode {
  constructor(public item: ItemDTO,
              public level = 1,
              public expandable = false,
              public loadMoreParentItem: ItemDTO | null = null) {}
}

/**
 * A database that only load part of the data initially. After user clicks on the `Load more`
 * button, more data will be loaded.
 */
@Injectable()
export class LoadmoreDatabase {
  batchNumber = 5;
  dataChange = new BehaviorSubject<LoadmoreNode[]>([]);
  nodeMap = new Map<ItemDTO, LoadmoreNode>();

  /** The data */
  rootLevelNodes =  new Array<ItemDTO>();
  dataMap = new Map<ItemDTO, ItemDTO[]>();

  initialize() {
    const data = this.rootLevelNodes.map(name => this._generateNode(name));
    this.dataChange.next(data);
  }

  setRoot(item: ItemDTO){
    this.rootLevelNodes.push(item);
  }

  addItem(father: ItemDTO, children: ItemDTO[]){
    this.dataMap.set(father,children);
  }

  /** Expand a node whose children are not loaded */
  loadMore(item: ItemDTO, onlyFirstTime = false) {
    if (!this.nodeMap.has(item) || !this.dataMap.has(item)) {
      return;
    }
    const parent = this.nodeMap.get(item)!;
    const children = this.dataMap.get(item)!;
    if (onlyFirstTime && parent.children!.length > 0) {
      return;
    }
    const newChildrenNumber = parent.children!.length + this.batchNumber;
    const nodes = children.slice(0, newChildrenNumber)
      .map(name => this._generateNode(name));

    parent.childrenChange.next(nodes);
    this.dataChange.next(this.dataChange.value);
  }

  private _generateNode(item: ItemDTO): LoadmoreNode {
    if (this.nodeMap.has(item)) {
      return this.nodeMap.get(item)!;
    }
    const result = new LoadmoreNode(item, this.dataMap.has(item));
    this.nodeMap.set(item, result);
    return result;
  }
}

@Component({
  selector: 'app-item-show',
  templateUrl: './item-show.component.html',
  styleUrls: ['./item-show.component.css'],
  providers: [LoadmoreDatabase,NgbModalConfig, NgbModal]
})
export class ItemShowComponent implements OnInit {
  
  public itemDTO: ItemDTO;
  public nameWbs: string;
  private wbsDto: WBSDTO;
  private idFather: number;

  nodeMap = new Map<ItemDTO, LoadmoreFlatNode>();
  treeControl: FlatTreeControl<LoadmoreFlatNode>;
  treeFlattener: MatTreeFlattener<LoadmoreNode, LoadmoreFlatNode>;
  // Flat tree data source
  dataSource: MatTreeFlatDataSource<LoadmoreNode, LoadmoreFlatNode>;

  constructor(private config: NgbModalConfig, private modalService: NgbModal, private database: LoadmoreDatabase, private router: Router, private itemService: ItemService) {
    config.backdrop = 'static';
    config.keyboard = false;
    
    this.treeFlattener = new MatTreeFlattener(this.transformer, this.getLevel,
    this.isExpandable, this.getChildren);

    this.treeControl = new FlatTreeControl<LoadmoreFlatNode>(this.getLevel, this.isExpandable);

    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);

    database.dataChange.subscribe(data => {
      this.dataSource.data = data;
    });
  }

  getChildren = (node: LoadmoreNode): Observable<LoadmoreNode[]> => node.childrenChange;

  transformer = (node: LoadmoreNode, level: number) => {
    const existingNode = this.nodeMap.get(node.item);

    if (existingNode) {
      return existingNode;
    }

    const newNode =
        new LoadmoreFlatNode(node.item, level, node.hasChildren, node.loadMoreParentItem);
    this.nodeMap.set(node.item, newNode);
    return newNode;
  }

  getLevel = (node: LoadmoreFlatNode) => node.level;

  isExpandable = (node: LoadmoreFlatNode) => node.expandable;

  hasChild = (_: number, _nodeData: LoadmoreFlatNode) => _nodeData.expandable;

  /** Load more nodes from data source */
  loadMore(item: ItemDTO) {
    this.database.loadMore(item);
  }

  loadChildren(node: LoadmoreFlatNode) {
    this.database.loadMore(node.item, true);
  }

  ngOnInit() {
    this.nameWbs = sessionStorage.getItem("nameWbs");
    this.wbsDto = new WBSDTO(parseInt(sessionStorage.getItem("idWbs")), "", 0);
    this.itemDTO = new ItemDTO(null,null,this.wbsDto.id,null,null);
    
    this.itemService.showItemTree(parseInt(sessionStorage.getItem("idWbs"))).subscribe((data: ItemDTO) => {
      if (data != null) {
        this.database.setRoot(data);
        this.database.addItem(data,data.items);
        this.showTree(data.items);
      }
    });
  }

  showTree(list: ItemDTO[]){
    for(let item of list){
      if(item.items.length > 0){
        this.database.addItem(item,item.items);
        this.showTree(item.items);
      }
    }
    this.database.initialize();
  }

  insertItem() {
    this.itemDTO.setFatherId(this.idFather);
    
    this.itemService.insertItem(this.itemDTO).subscribe((data: ItemDTO) => {
      location.reload(true);
    });
  }

  deleteItem(idItem: number) {
    if(confirm("Se cancelli questo nodo, cancellerai anche i figli. Confermi?")){
      this.itemDTO = new ItemDTO(idItem, "", 0, 0, null);
      this.itemService.deleteItem(idItem).subscribe((data: any) => {
        location.reload(true);
      });
    }
    
  }

  inserisciRadice() {
    sessionStorage.setItem("idFather", JSON.stringify(0));
    this.router.navigateByUrl("itemInsert");
  }

  open(idItem: number, content) {
    this.idFather = idItem;
    console.log("father: "+idItem);
    this.modalService.open(content);
  }
}
