
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
  selector: 'app-providers',
  templateUrl: './providers.component.html',
  styleUrls: ['./providers.component.css']
})
export class ProvidersComponent implements OnInit {
  public packagess: packages[] | undefined;
  public channels: Channel[] | undefined;
  public providers: providers | undefined;
  constructor(private providersService: ProvidersService,private modalService: NgbModal,private packageService: PackageService, private channelService: ChannelService) { }
  closeResult = '';

  ngOnInit(): void {
    this.getChannels();
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
// public onListSend(contractFormAddChannels : NgForm){
//   let channel: Channel [] = contractFormAddChannels.controls['channel'].value;
//   this.providerByid(this.SelectedPackageId!);
//   for(let i=0; i<channel.length; i++){
//     this.addChannelstopackage(this.SelectedPackageId!,channel[i].id, this.packages);
//   }
// }

public addChannelstoProvider(idAPck: number, idChan: number, providers: providers){
  this.providersService.addChannelsToProviders(idAPck, idChan,providers).subscribe(


  )

}

public providerByid(id: number): void{
  this.providersService.getProviderById(id).subscribe(
    (response: providers) => {
      this.providers = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

}
