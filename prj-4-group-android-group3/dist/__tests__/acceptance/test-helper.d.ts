import { Prj4GroupAndroidGroup3Application } from '../..';
import { Client } from '@loopback/testlab';
export declare function setupApplication(): Promise<AppWithClient>;
export interface AppWithClient {
    app: Prj4GroupAndroidGroup3Application;
    client: Client;
}
