import { providers } from "./providers";

export interface Channel{
  id: number
  name: string;
  type: string;
  price: number;
pack_id: number;
providers: providers;
}