import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../models/file-handle';
import { RestaurantRegister } from '../models/restaurant-register';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(restaurant:RestaurantRegister){
    const restImages: any[] = restaurant.restaurantimage;

    const restImagesToFileHandle : FileHandle[] = [];

    for (let i=0; i<restImages.length; i++ )
    {
      const imageFileData = restImages[i];

      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);

      const imageFile = new File( [imageBlob], imageFileData.name, {type: imageFileData.type});

      const finalFileHandle : FileHandle = {
        file: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      restImagesToFileHandle.push(finalFileHandle);
    }

    restaurant.restaurantimage = restImagesToFileHandle;
    return restaurant;
  }

  public dataURItoBlob(picBytes: string, imageType: any) {
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for (let i=0; i<byteString.length; i++ )
    {
      int8Array[i] = byteString.charCodeAt(i);
    }

    const blob = new Blob([int8Array], {type:imageType});
    return blob;
  }
}
