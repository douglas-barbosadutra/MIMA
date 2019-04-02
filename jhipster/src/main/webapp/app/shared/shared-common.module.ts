import { NgModule } from '@angular/core';

import { MimaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [MimaSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [MimaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class MimaSharedCommonModule {}
