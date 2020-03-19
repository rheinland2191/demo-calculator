import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  constructor(
    private http: HttpClient,
  ) {

  }

  getCars() {
    return this.http.get('http://localhost:8080/cars',{observe: 'response'});
  }

  editCars(data,id) {
    return this.http.put('http://localhost:8080/cars/'+id, data,{observe: 'response'});
  }

  addCars(data) {
    return this.http.post('http://localhost:8080/cars', data,{observe: 'response'});
  }

  deleteCars(id) {
    return this.http.delete('http://localhost:8080/cars/' + id,{observe: 'response'});
  }

  calcPrice(data) {
    return this.http.post('http://localhost:8080/calc-price', data,{observe: 'response'})
     .pipe(
       retry(1),
       catchError(this.handleError)
     );
  }

  getCustomers() {
    return this.http.get('/api/customer');
  }

  putCustomer(data: Object) {
    return this.http.put('/api/customer', data);
  }

  postCustomer(data: Object) {
    return this.http.post('/api/customer', data);
  }

  deleteCustomer(id: String) {
    return this.http.delete('/api/customer' + id);
  }

  getOrders() {
    return this.http.get('/api/order');
  }

  postOrder(data: Object) {
    return this.http.post('/api/order', data);
  }
   handleError(error) {
     console.log(error)
   let errorMessage = '';
   if (error.error instanceof ErrorEvent) {
     // client-side error
     errorMessage = `Error: ${error.error.message}`;
   } else {
     // server-side error
     errorMessage = `${error.error.details}`;
   }
   window.alert(errorMessage);
   return throwError(errorMessage);
 }
}
