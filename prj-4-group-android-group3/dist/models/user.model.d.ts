import { Entity } from '@loopback/repository';
export declare class User extends Entity {
    id?: string;
    email?: string;
    name?: string;
    password?: string;
    role?: string;
    [prop: string]: any;
    constructor(data?: Partial<User>);
}
export interface UserRelations {
}
export declare type UserWithRelations = User & UserRelations;
