import { Channel } from "./channel"
import { packages } from "./packages"


export interface contract{
    id: number
    startDate: Date
    endDate: Date
    monthly_price: number
    price: number
    channelsInContract: Channel[]
    packagesInContract: packages[]
    name: string
  }

