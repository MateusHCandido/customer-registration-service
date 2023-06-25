import { RouterModule, Routes } from "@angular/router";
import { ServiceFormComponent } from "./service-form/service-form.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
    { path: 'service-form', component: ServiceFormComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ServicesRoutingModule { }