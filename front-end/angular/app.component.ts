import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Channel } from './channel';
import { ChannelService } from './channel.service';
import { PackageService } from './package.service';
import { packages } from './packages';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title: any;
  // public channels: Channel[] | undefined;
  // public packagess: packages[] | undefined;
  constructor(private channelService: ChannelService,private packageService: PackageService) {}
  ngOnInit(): void {
    //  this.getChannels();
    // this.getPackages();
  }
 

  
  


  
// public getChannelsByPackage(id: number): void{
//   this.channelService.getChannelByPackage(id).subscribe(
//     (response: Channel[]) => {
//       this.channels = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   )
// }



// public getPackages(): void{
//   this.packageService.getpackages().subscribe(
//     (response: packages[]) => {
//       this.packagess = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   )
// }

// public getChannels(): void{
//   this.channelService.getChannel().subscribe(
//     (response: Channel[]) => {
//       this.channels = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   )
// }


// public onAddChannel(addForn: NgForm): void{
//   this.channelService.addChannel(addForn.value).subscribe(
//     (response: Channel) =>{
//       console.log(response);
//       this.getChannels;
//     },
//     (error: HttpErrorResponse) =>{
//       alert(error.message);
//     }
//   );
// }



// public addChannelsWithPackage(name: String, id: number): void{
//   this.packageService.addChannelsToPackages(name,id).subscribe(
//     (response: Channel[]) => {
//       this.channels = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   )
// }

// public onaddChannelsWithPackage(addChannelsWithPackage: NgForm): void{
//   this.packageService.addChannelsToPackages(addFormToPackages.value).subscribe(
//     (response: packages[]) => {
//       this.packagess = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   );
// }

}
