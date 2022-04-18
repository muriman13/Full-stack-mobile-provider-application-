import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { Channel } from './channel';
import { environment } from 'src/environments/environment';
import { packages } from './packages';
@Injectable({
  providedIn: 'root'
})
export class PackageService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
    public getpackages(): Observable<packages[]>{
    return this.http.get<packages[]>('http://localhost:8080/sho');
   }
   public addpackages(packages: packages): Observable<packages>{
    return this.http.post<packages>('http://localhost:8080/savePostpackages', packages);
   }

   public addChannelsToPackages(name: String, id: number):Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/addto/' + name +'/'+ id);
  }
  public getChannelByPackage(id: number): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/request/' + id);
   }
   public getPackageByid(id: number): Observable<packages[]>{
    return this.http.get<packages[]>('http://localhost:8080/sho/' + id);
   }
} 
   