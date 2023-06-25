import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './template/footer/footer.component';
import { CustomersModule } from './customers/customers.module';
import { ServicesModule } from './services/services.module';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TemplateModule,
    CustomersModule,
    ServicesModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
