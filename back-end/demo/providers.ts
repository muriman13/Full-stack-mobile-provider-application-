import { Channel } from "./channel";

export interface providers{
        id: number;
        name: string;
        price: number;
        channels: Channel [];
    }