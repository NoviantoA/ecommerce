import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from './_model/file-handle.model';
import { Directive, EventEmitter, HostBinding, HostListener, Output } from '@angular/core';

@Directive({
  selector: '[appDrag]'
})
export class DragDirective {

  @Output()
  files: EventEmitter<FileHandle> = new EventEmitter();

  @HostBinding("style.background")
  private background = "#eee";

  constructor(private sanitizer: DomSanitizer) { }

  @HostListener("dragover", ["$event"])
  public onDragOver(evnt: DragEvent){
    evnt.preventDefault();
    evnt.stopPropagation();
    this.background = "#999";
  }

  @HostListener("dragLeave", ["$event"])
  public onDragLeave(evnt: DragEvent){
    evnt.preventDefault();
    evnt.stopPropagation();
    this.background = "eee";
  }

  @HostListener("drop", ["$event"])
  public onDrop(evnt: DragEvent){
    evnt.preventDefault();
    evnt.stopPropagation();
    this.background = "#eee";

    let fileHandle: FileHandle = null;
    const file = evnt.dataTransfer.files[0];
    const url = this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(file));
    fileHandle = {file, url};
    this.files.emit(fileHandle);
  }

}
