import { Component, OnInit } from '@angular/core';
import { Channel } from '../channel';
import { packages } from '../packages';
import { ChannelService } from '../channel.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { PackageService } from '../package.service';
import {NgbModal, ModalDismissReasons, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { ProvidersService } from '../providers.service';
import { providers } from '../providers';

@Component({
  selector: 'app-channel-list',
  templateUrl: './channel-list.component.html',
  styleUrls: ['./channel-list.component.css']
})
export class ChannelListComponent implements OnInit {

  public channels: Channel[] | undefined;
  public packagess: packages[] | undefined;
  public channel: Channel | undefined;
  public providerss: providers[] | undefined;
  public percent: number | undefined;
  public channelsBypackage: Channel[] | undefined;
  constructor(private channelService: ChannelService, private packageService: PackageService,private modalService: NgbModal, private providersService: ProvidersService) {}
  closeResult = '';
  ngOnInit(): void {
    this.getChannels();
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

  public getChannelByid(id: number): void{
    this.channelService.getChannelByid(id).subscribe(
      (response: Channel) => {
        this.channel = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public update(channel: Channel){
    this.channelService.update(channel).subscribe(
    (Response: Channel) =>{
      console.log(Response);
    }
    )
  }

 
  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', windowClass : "myCustomModalClass",size: 'lg'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
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
  
  public onAddChannel(addForm: NgForm): void{
    this.getChannels;
    this.channelService.addChannel(addForm.value).subscribe(
      (response: Channel) =>{
        console.log(response);
        
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
       
      }
    );
  }
  

}
