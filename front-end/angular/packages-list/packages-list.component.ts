import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Channel } from '../channel';
import { ChannelService } from '../channel.service';
import { PackageService } from '../package.service';
import { packages } from '../packages';

@Component({
  selector: 'app-packages-list',
  templateUrl: './packages-list.component.html',
  styleUrls: ['./packages-list.component.css']
})
export class PackagesListComponent implements OnInit {
  public packagess: packages[] | undefined;
  public channels: Channel[] | undefined;
  constructor(private packageService: PackageService, private channelService: ChannelService) { }
  

  ngOnInit(): void {
    this.getPackages
  }
  
  
  



public getPackages(): void{
  this.packageService.getpackages().subscribe(
    (response: packages[]) => {
      this.packagess = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

public getChannelsByPackage(id: number): void{
  this.channelService.getChannelByPackage(id).subscribe(
    (response: Channel[]) => {
      this.channels = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}


public addChannelsWithPackage(name: String, id: number): void{
  this.packageService.addChannelsToPackages(name,id).subscribe(
    (response: Channel[]) => {
      this.channels = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

public getChannels(): void{
  this.channelService.getChannel().subscribe(
    (response: Channel[]) => {
      this.channels = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

}
