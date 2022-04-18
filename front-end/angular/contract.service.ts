import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { contract } from './contract';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
  public addChanneltoConcrat(  conc: number,chanId: number): Observable<contract>{
    return this.http.put<contract>('http://localhost:8080/contract/addChannel/'+ conc+'/' + chanId, this.getbyid(conc));
   }
   public addPackagetoConcrat(  conc: number,packId: number): Observable<contract>{
    return this.http.put<contract>('http://localhost:8080/contract/addPackage/'+ conc +'/'+ packId, this.getbyid(conc));
   }
   public getbyid(id: number): Observable<contract>{
    return this.http.get<contract>('http://localhost:8080/contract/byid/' + id);
   }
}
