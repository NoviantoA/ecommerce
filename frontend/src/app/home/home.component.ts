import { Component, OnInit } from '@angular/core';
import {ProductService} from '../_services/product.service';
import {map} from 'rxjs/operators';
import {Product} from '../_model/product.model';
import {error} from 'protractor';
import {HttpErrorResponse} from '@angular/common/http';
import {ImageProcessingService} from '../image-processing.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productDetails = [];

  constructor(private  productService: ProductService,
              private imageProcessingService: ImageProcessingService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  // tslint:disable-next-line:typedef
  public getAllProducts() {
    this.productService.getAllProducts()
        .pipe(
            map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
        )
        .subscribe(
            (response: Product[]) => {
              console.log(response);
              this.productDetails = response;
              // tslint:disable-next-line:no-shadowed-variable
            }, (error: HttpErrorResponse) => {
              console.log(error);
            }
        );
  }

}
