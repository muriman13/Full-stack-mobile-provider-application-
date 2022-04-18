import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { Channel } from './channel';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}
    public getChannel(): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/show');
   }
   public addChannel(channel: Channel): Observable<Channel>{
    return this.http.post<Channel>('http://localhost:8080/savePost', channel);
   }
   public getChannelByPackage(id: number): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/request/' + id);
   }
   public getChannelByCategory(id: string): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/category/' + id);
   }
   public getChannelByid(id: number): Observable<Channel[]>{
    return this.http.get<Channel[]>('http://localhost:8080/show/' + id);
   }

  

} 
   