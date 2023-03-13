import { ImageProcessingService } from './../image-processing.service';
import { ShowImagesDialogComponent } from './../show-images-dialog/show-images-dialog.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Product } from './../_model/product.model';
import { ProductService } from './../_services/product.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-show-product-ddetails',
  templateUrl: './show-product-ddetails.component.html',
  styleUrls: ['./show-product-ddetails.component.css']
})
export class ShowProductDdetailsComponent implements OnInit {
  productDetails: Product[] = [];
  displayedColumns: string[] = ['Id', 'Nama Produk', 'Deskrisi Produk', 'Harga Diskon', 'Harga Awal', 'Images', 'Edit', 'Delete'];

  constructor(private productervice: ProductService, public imagesDialog:MatDialog,
    private imageProcessingService: ImageProcessingService) { }

  ngOnInit(): void {
    this.getAllProduct();
  }

  public getAllProduct(){
    this.productervice.getAllProducts()
    .pipe(
      map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    )
    .subscribe(
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

  showImages(product: Product) {
    console.log(product);
    this.imagesDialog.open(ShowImagesDialogComponent, {
      data: {
        images: product.productImages
      },
      height: '500px',
      width: '800px'
    })
  }

}
