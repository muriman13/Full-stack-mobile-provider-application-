import { Channel } from "./channel";
import { providers } from "./providers";

export interface packages{
  id: number;
    name: string;
    price: number;
  channels: Channel [];
  providersInpackage: providers;
  }