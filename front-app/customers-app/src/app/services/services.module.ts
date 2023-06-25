import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ServiceFormComponent } from './service-form/service-form.component';
import { ServicesRoutingModule } from './services-routing.module';



@NgModule({
  declarations: [ServiceFormComponent],
  imports: [
    CommonModule,
    ServicesRoutingModule
  ],
  exports: [ServiceFormComponent]
})
export class ServicesModule { }
