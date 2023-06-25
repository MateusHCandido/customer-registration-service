import { Component, OnInit } from '@angular/core';

import { CustomerRequest } from './customer';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {

  customer: CustomerRequest;

  constructor() {
    this.customer = new CustomerRequest();
   }

  ngOnInit(): void {
  }


  onSubmit(){
    console.log(this.customer)
  }
}
