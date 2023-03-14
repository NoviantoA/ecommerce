import { Product } from '../_model/product.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  // tslint:disable-next-line:typedef
  public addProduct(product: FormData) {
    return this.httpClient.post<Product>('http://localhost:9090/api/addNewProduct', product);
  }

  // tslint:disable-next-line:typedef
  public getAllProducts(){
    return this.httpClient.get<Product[]>('http://localhost:9090/api/getAllProduct');
  }

  // tslint:disable-next-line:typedef
  public deleteProduct(productId: number){
    return this.httpClient.delete('http://localhost:9090/api/deleteProductDetails/' + productId);
  }

  // tslint:disable-next-line:typedef
  public getProductDetailsById(productId){
    return this.httpClient.get<Product>('http://localhost:9090/api/getProductDetailsById/' + productId);
  }
}
