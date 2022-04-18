import { Component, OnInit } from '@angular/core';
import { Channel } from '../channel';
import { packages } from '../packages';
import { ChannelService } from '../channel.service';
import { PackageService } from '../package.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ContractService } from '../contract.service';
import { contract } from '../contract';
@Component({
  selector: 'app-contracct-list',
  templateUrl: './contracct-list.component.html',
  styleUrls: ['./contracct-list.component.css']
})
export class ContracctListComponent implements OnInit {

  public channels: Channel[] | undefined;
  public channelsForContract: Channel[] | undefined;
  public packagesForContract: packages[] | undefined;
  public packagess: packages[] | undefined;
  contract: contract | undefined;
  constructor(private channelService: ChannelService, private packageService: PackageService, private contractService: ContractService) {}


  ngOnInit(): void {
    this.getChannels();
    this.getPackages();
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

  public addChannelToContract(id: number): void{
    this.channelService.getChannelByid(id).subscribe(
      (response: Channel[]) => {
        this.channelsForContract = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
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
  public addContract(conc: number,ChanId: number): void{
  this.contractService.addChanneltoConcrat(conc,ChanId).subscribe(
    (response: contract) => {
      this.contract = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}
public getChannelByid(id: number): void{
  this.channelService.getChannelByid(id).subscribe(
    (response: Channel[]) => {
      this.channels = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}
public getChannelByidForCOntract(id: number): void{
  this.channelService.getChannelByid(id).subscribe(
    (response: Channel[]) => {
      this.channelsForContract = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}

public getPackageByidForCOntract(id: number): void{
 this.packageService.getPackageByid(id).subscribe(
    (response: packages[]) => {
      this.packagesForContract = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
}


}
