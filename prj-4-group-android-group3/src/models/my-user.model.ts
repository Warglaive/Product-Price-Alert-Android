import {Entity, model, property} from '@loopback/repository';

@model({settings: {strict: false}})
export class MyUser extends Entity {
  @property({
    type: 'string',
    id: true,
    generated: true,
  })
  id?: string;

  @property({
    type: 'string',
  })
  email?: string;

  @property({
    type: 'string',
  })
  name?: string;

  @property({
    type: 'string',
  })
  password?: string;

  @property({
    type: 'string',
  })
  role?: string;

  // Define well-known properties here

  // Indexer property to allow additional data
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [prop: string]: any;

  constructor(data?: Partial<MyUser>) {
    super(data);
  }
}

export interface MyUserRelations {
  // describe navigational properties here
}

export type MyUserWithRelations = MyUser & MyUserRelations;
