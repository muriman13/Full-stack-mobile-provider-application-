import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Channel } from '../channel';
import { ChannelService } from '../channel.service';
import { PackageService } from '../package.service';
import { packages } from '../packages';
import { providers } from '../providers';
import { ProvidersService } from '../providers.service';

@Component({
  selector: 'app-packages-list',
  templateUrl: './packages-list.component.html',
  styleUrls: ['./packages-list.component.css']
})
export class PackagesListComponent implements OnInit {
  public packagess: packages[] | undefined;
  public channels: Channel[] | undefined;
  public providerss: providers[] | undefined;
  constructor(private providersService: ProvidersService,private modalService: NgbModal,private packageService: PackageService, private channelService: ChannelService) { }
  closeResult = '';

  ngOnInit(): void {
    this.getPackages();
    this.getproviders();
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

public onAddPackage(addPackage: NgForm): void{
  this.getPackages;
  this.packageService.addpackages(addPackage.value).subscribe(
    (response: packages) =>{
      console.log(response);
     
      location.reload();
    },
    (error: HttpErrorResponse) =>{
      alert(error.message);
      
    }
  );
 
}

public getproviders(): void{
  this.providersService.getAllProviders().subscribe(
    (response: providers[]) => {
      this.providerss = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

open(content: any) {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', windowClass : "myCustomModalClass",size: 'lg'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
}
private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}
}

