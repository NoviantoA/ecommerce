import { ShowProductDdetailsComponent } from './show-product-ddetails/show-product-ddetails.component';
import { AddNewProductComponent } from './add-new-product/add-new-product.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './_auth/auth.guard';
import {ProductResolveService} from './product-resolve.service';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard], data: {roles: ['Admin']} },
  { path: 'user', component: UserComponent ,  canActivate: [AuthGuard], data: {roles: ['User']} },
  { path: 'login', component: LoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: 'addNewProduct', component: AddNewProductComponent, canActivate: [AuthGuard], data: {roles: ['Admin']},
    resolve: {
    product: ProductResolveService
    }
  },
  { path: 'showProductDetails', component: ShowProductDdetailsComponent, canActivate: [AuthGuard], data: {roles: ['Admin']}},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
