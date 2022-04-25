import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Channel } from './channel';
import { contract } from './contract';
import { packages } from './packages';

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
   public getChannelsOfContract(id: number): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/contract/show/' + id);
   }
   public saveContractAndGetIdEmpty(contract: contract):Observable <contract>{
    return this.http.post<contract>('http://localhost:8080/contract/saveIdEmpty/', contract);
  }
   public saveContract(contract: contract):Observable <contract>{
    return this.http.post<contract>('http://localhost:8080/contract/saveId/', contract);
  }
  public updateContract( contract: contract): Observable<contract>{
    return this.http.put<contract>('http://localhost:8080/contract/update', contract);
   }
   public getChannelsInContract(id: number): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/contract/ChannelsInContract/' + id);
   }
   public getPackagesInContract(id: number): Observable<packages[]>{
    return this.http.get<packages[]>('http://localhost:8080/contract/packagesInContract/' + id);
   }
}
