package service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pCarpet.converter.ItemConverter;
import com.pCarpet.converter.WBSConverter;
import com.pCarpet.dao.ItemDAO;
import com.pCarpet.dao.WBSDAO;
import com.pCarpet.dto.ItemDTO;
import com.pCarpet.dto.WBSDTO;
import com.pCarpet.services.ItemService;
import com.pCarpet.services.WBSService;


public class WBSTest {
	
	@Mock
	private WBSDAO wbsDao;
		
	@Mock
	private ItemService itemService;
	
	@InjectMocks
	private WBSService wbsService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testInsertWBS() {
		WBSDTO wbs = new WBSDTO(0, "prova", 1);
		ItemDTO item = new ItemDTO(0, "test", 0, 1, null);
		when(wbsDao.saveAndFlush(WBSConverter.convertToEntity(wbs))).thenReturn(WBSConverter.convertToEntity(wbs));
		doReturn(item).when(itemService).insertItem(item);						//questa sintassi è equivalente a quella della riga precedente
		assertThat(wbsService.insertWBS(wbs), is (wbs));
	}
}
