import { Component, OnInit, ViewChild, Renderer2 , ChangeDetectorRef,AfterViewInit} from '@angular/core';
import { AppService } from './service/app.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder, Validators, AbstractControl, FormControl } from '@angular/forms';
import { Cars,Calculator,CalculatorSubmit } from 'src/app/car.model';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class AppComponent implements OnInit,AfterViewInit{
  title = 'calculator-frontend';
  isLoading = false;
  isDisplay = false;
  isDisplayError = false;
  editForm: FormGroup;
  addForm: FormGroup;
  deleteForm: FormGroup;
  calculatorForm: FormGroup;
  message : String;

  constructor(private api: AppService,private config: NgbModalConfig
  	, private modalService: NgbModal,private fb: FormBuilder, private cdRef : ChangeDetectorRef){
  	this.createForm();
  }
  cars: any = [];
  editCars: Cars;
  deleteCars: Cars;
  addCars: Cars;
  calculator: Calculator[] = [];
  calculatorSubmit: CalculatorSubmit[] = [];
  totalPrice : number = 0;
  ngOnInit() {
  	console.log(this.cars)
    this.getCars();

  }
 ngAfterViewInit() {

 this.cdRef.detectChanges();
  }
  getCars(){
  	this.api.getCars().subscribe((response) => {
    	console.log(response)
      this.cars = response.body;
    });
  }
  createForm() {
    this.editForm = this.fb.group({
      carId: ['', [
      ]],
      name: ['', [
        Validators.required,
      ]],
      price: ['',[
        Validators.required, 
        Validators.min(10000) 
      ]],
      qty: ['',[
        Validators.required,  
        Validators.min(1)
      ]],
      yearBuild: ['',[
        Validators.required, 
        Validators.min(1900), 
        Validators.max(2020) 
      ]]
    });
    this.addForm = this.fb.group({
      carId: ['', [
      ]],
      name: ['', [
        Validators.required,
      ]],
      price: ['',[
        Validators.required, 
        Validators.min(10000) 
      ]],
      qty: ['',[
        Validators.required,  
        Validators.min(1)
      ]],
      yearBuild: ['',[
        Validators.required, 
        Validators.min(1900), 
        Validators.max(2020) 
      ]]
    });

    this.deleteForm = this.fb.group({
      carId: ['', [
      ]],
      name: ['', [
        Validators.required,
      ]],
      price: ['',[
        Validators.required, 
        Validators.min(10000) 
      ]],
      qty: ['',[
        Validators.required,  
        Validators.min(1)
      ]],
      yearBuild: ['',[
        Validators.required, 
        Validators.min(1900), 
        Validators.max(2020) 
      ]]
    });

    this.calculatorForm = this.fb.group({
      carId: ['', [
      // [Validators.required
      ]],
      day: ['',[
        // Validators.required, 
        // Validators.min(1) 
      ]],
      qty: ['',[
        // Validators.required,  
        // Validators.min(1)
      ]]
    });
  }
  editCar(content,car) {
  	this.editCars=car;
    this.modalService.open(content);
  }
  submitEdit(){
  	console.log(this.editCars);
  	this.isLoading = true;
  	this.api.editCars(this.editCars,this.editCars.carId).subscribe((response)=>{
  		console.log(response);
  		if(response.status === 200){
  			this.isLoading = false;
  			this.modalService.dismissAll();
  			this.getCars();
  		}
  	})
  }

  addCar(content){
  	// this.addCars = {"carId": null,"name": null,"price": null,"qty": null,"yearBuild": null}
    this.modalService.open(content);
  }
  submitAdd(){
  	console.log(this.addCars);
  	this.isLoading = true;
  	this.api.addCars(this.addCars).subscribe((response)=>{
  		console.log(response);
  		if(response.status === 200){
  			this.isLoading = false;
  			this.modalService.dismissAll();
  			this.getCars();
  		}
  	})
  }
  deleteCar(content,car){
  	this.deleteCars=car;
    this.modalService.open(content);
  }
  submitDelete(){
  	console.log(this.deleteCars);
  	this.isLoading = true;
  	this.api.deleteCars(this.deleteCars.carId).subscribe((response)=>{
  		console.log(response);
  		if(response.status === 200){
  			this.isLoading = false;
  			this.modalService.dismissAll();
  			this.getCars();
  		}
  	})
  }

  addData(){
  	console.log(this.calculator)
  	this.calculator.push({
    "carId": null,
    "day": '0',
    "qty": '0'});
  }
  setCar(id,index){
  	this.calculator[index].carId=id;
  }

  calc(){
  	this.calculatorSubmit = [];
    this.totalPrice = 0;
  	console.log(this.calculator);
  	this.calculator.forEach((data: Calculator) => {
  		let cars = {carId:0,day:0,qty:0}
  		cars.carId = data.carId;
  		cars.day = parseInt(data.day);
  		cars.qty = parseInt(data.qty);
  		this.calculatorSubmit.push(cars);
    })
    console.log(this.calculatorSubmit)
  	this.api.calcPrice(this.calculatorSubmit).subscribe((response)=>{
  		console.log(response);
  		if(response.status === 200){
  			this.isDisplay = true;
  			this.totalPrice = response.body.total;
  			// this.modalService.dismissAll();
  			// this.getCars();
  		}else if(response.status === 404){
  			console.log("tes")
  			this.isDisplayError = true;
  			this.message = response.message;
  		}
  	})
  }
 
} 
