import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { providers } from './providers';
import { Channel } from './channel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProvidersService {

  constructor(private http: HttpClient) { }
  public addChannelsToProviders(idPack: number, id: number,providers: providers):Observable<Channel[]>{
    return this.http.put<Channel[]>('http://localhost:8080/providers/addto/' + idPack +'/'+ id , providers);
  }
  public addProvider(providers: providers): Observable<providers>{
    return this.http.post<providers>('http://localhost:8080/providers/save', providers);
  }
  public getProviderById(id: number): Observable<providers>{
    return this.http.get<providers>('http://localhost:8080/providers/getbyId/' + id);
   }
   public getAllProviders(): Observable<providers[]>{
     return this.http.get<providers[]>('http://localhost:8080/providers/getall')
   }
}
