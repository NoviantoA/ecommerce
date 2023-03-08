import { Product } from './../_model/product.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  public addProduct(product: Product) {
    return this.httpClient.post<Product>("http://localhost:9090/api/addNewProduct", product);
  }
}