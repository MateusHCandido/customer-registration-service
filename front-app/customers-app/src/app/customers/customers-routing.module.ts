import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CustomerFormComponent } from './customer-form/customer-form.component';

const routes: Routes = [
  { path: 'customer-form', component: CustomerFormComponent }

  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomersRoutingModule { }
