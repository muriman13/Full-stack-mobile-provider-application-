import { Component, OnInit } from '@angular/core';
import { Channel } from '../channel';
import { packages } from '../packages';
import { ChannelService } from '../channel.service';
import { PackageService } from '../package.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ContractService } from '../contract.service';
import { contract } from '../contract';
import {NgbModal, ModalDismissReasons, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { FormControl, NgForm } from '@angular/forms';
import { empty } from 'rxjs';
import { HttpClient } from '@angular/common/http';
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
  public contract: contract | undefined;
  public contracts: contract[] | undefined;
  public idOfContract: number | undefined;
  channel: Channel | undefined;
  contractEmpty: contract | undefined;
  constructor(private channelService: ChannelService, private packageService: PackageService, private contractService: ContractService,private modalService: NgbModal,private http: HttpClient) {}
  CloseResult = '';

  ngOnInit(): void {
    this.getChannels();
    this.getPackages();
  }

  public  getIdOfCreatedContract(){
    this.http.post<number>('http://localhost:8080/contract/saveId/', { title: 'Angular POST Request Example' }).subscribe(data => {
        this.idOfContract = data;
     
    })
  }
  public onListSend(contractFormAddChannels : NgForm){
   
    let channel: Channel [] = contractFormAddChannels.controls['channel'].value;
    this.contractService.getChannelsInContract(this.idOfContract!)
    for(let i=0; i<channel.length; i++){
      this.addChannelToContract(this.idOfContract!,channel[i].id);
     
    }
    this.getChannelsInContract(this.idOfContract!);
  }
  public onListSendPackage(contractFormAddPackage : NgForm){
    
    let packages: packages [] = contractFormAddPackage.controls['package'].value;
    
    this.getPackagesInCotract(this.idOfContract!);
    for(let i=0; i<packages.length; i++){
      this.addPackageToContract(this.idOfContract!,packages[i].id);
    }
    this.getPackagesInCotract(this.idOfContract!);
  }

  public getConId(){
    alert(this.idOfContract);
     return this.contract?.id!;
     
  }

  public updateContract(contract: contract){
this.contractService.updateContract(contract).subscribe(
(Response: contract) =>{
  console.log(Response);
  alert(contract.id as number);
}


)
  }

  public onAddContract(contractForm: NgForm): void{
    this.contractService.saveContract(contractForm.value).subscribe(
      (response: contract) =>{
        console.log(response);
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
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
  public getChannelsInContract( id: number): void{
    this.contractService.getChannelsInContract(id).subscribe(
      (response: Channel[]) => {
        this.channelsForContract = response;

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
  public getPackagesInCotract( id: number): void{
    
    this.contractService.getPackagesInContract(id).subscribe(
      (response: packages[]) => {
        this.packagesForContract = response;

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  // public addChannelToContract(id: number): void{
  //   this.channelService.getChannelByid(id).subscribe(
  //     (response: Channel[]) => {
  //       this.channelsForContract = response;
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   )
  // }

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
  public addChannelToContract(conc: number ,ChanId: number): void{
  this.contractService.addChanneltoConcrat(conc,ChanId).subscribe(
    (response: contract) => {
      this.contract = response;
      this.getChannelsInContract(this.idOfContract!);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
   this.getChannelsInContract(this.idOfContract!);
}

public addPackageToContract(conc: number ,PackId: number): void{
 
  this.contractService.addPackagetoConcrat(conc,PackId).subscribe(
    (response: contract) => {
      this.contract = response;
      this.getPackagesInCotract(this.idOfContract!);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  )
  this.getPackagesInCotract(this.idOfContract!);
}

// public getChannelByid(id: number): void{
//   this.channelService.getChannelByid(id).subscribe(
//     (response: Channel[]) => {
//       this.channels = response;
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   )
// }

// public saveContractAndGetId(contract: contract){
//   this.contractService.saveContractAndGetIdEmpty(contract).subscribe(
//    ( response: contract) => {
//     this.contract = response;
//    }
//   )
  
// }




  
public  getIndex(){
  return this.idOfContract;
}


open(content: any) {
  this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', windowClass : "myCustomModalClass",size: 'lg'}).result.then((result) => {
    this.CloseResult = `Closed with: ${result}`;
  }, (reason) => {
    this.CloseResult = `Dismissed ${this.getDismissReason(reason)}`;
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
