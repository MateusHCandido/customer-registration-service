import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CustomersRoutingModule } from './customers-routing.module';
import { CustomerFormComponent } from './customer-form/customer-form.component';



@NgModule({
  declarations: [CustomerFormComponent],
  imports: [
    CommonModule,
    CustomersRoutingModule,
    FormsModule
  ],
  exports: [
    CustomerFormComponent
  ]
})
export class CustomersModule { }
