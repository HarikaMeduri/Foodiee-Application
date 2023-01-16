import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../models/file-handle';
import { RestaurantFoods } from '../models/restaurant-foods';

@Injectable({
  providedIn: 'root'
})
export class FoodImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(foods:RestaurantFoods){
    const fooImages: any[] = foods.foodimage;

    const fooImagesToFileHandle : FileHandle[] = [];

    for (let i=0; i<fooImages.length; i++ )
    {
      const imageFileData = fooImages[i];

      const imageBlob = this.dataURItoBlob(imageFileData.picByte, imageFileData.type);

      const imageFile = new File( [imageBlob], imageFileData.name, {type: imageFileData.type});

      const finalFileHandle : FileHandle = {
        file: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      fooImagesToFileHandle.push(finalFileHandle);
    }

    foods.foodimage = fooImagesToFileHandle;
    return foods;
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
