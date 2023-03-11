import { HttpErrorResponse } from '@angular/common/http';
import { Product } from './../_model/product.model';
import { ProductService } from './../_services/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-show-product-ddetails',
  templateUrl: './show-product-ddetails.component.html',
  styleUrls: ['./show-product-ddetails.component.css']
})
export class ShowProductDdetailsComponent implements OnInit {
  productDetails: Product[] = [];
  displayedColumns: string[] = ['Id', 'Nama Produk', 'Deskrisi Produk', 'Harga Diskon', 'Harga Awal', 'Edit', 'Delete'];

  constructor(private productervice: ProductService) { }

  ngOnInit(): void {
    this.getAllProduct();
  }

  public getAllProduct(){
    this.productervice.getAllProducts().subscribe(
      (response: Product[]) => {
        console.log(response);
        this.productDetails = response;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  deleteProduct(productId){
    this.productervice.deleteProduct(productId).subscribe(
      (response) => {
        this.getAllProduct();
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

}
