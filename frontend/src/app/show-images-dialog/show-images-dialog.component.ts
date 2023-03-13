import { FileHandle } from './../_model/file-handle.model';
import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-show-images-dialog',
  templateUrl: './show-images-dialog.component.html',
  styleUrls: ['./show-images-dialog.component.css']
})
export class ShowImagesDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.receiveImage();
  }

  receiveImage(){
    console.log(this.data);
  }

}
