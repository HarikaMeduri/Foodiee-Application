import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-searchfood',
  templateUrl: './searchfood.component.html',
  styleUrls: ['./searchfood.component.css']
})
export class SearchfoodComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  enteredSearchValue:string="";

  @Output()
  searchTextChanged: EventEmitter<string>=new EventEmitter<string>();
  
  onSearchTextChanged(){
    this.searchTextChanged.emit(this.enteredSearchValue);
    
  }
}
