import {inject} from '@loopback/core';
import {DefaultCrudRepository} from '@loopback/repository';
import {MongoDbDataSource} from '../datasources';
import {MyUser, MyUserRelations} from '../models';

export class MyUserRepository extends DefaultCrudRepository<
  MyUser,
  typeof MyUser.prototype.id,
  MyUserRelations
> {
  constructor(
    @inject('datasources.MongoDB') dataSource: MongoDbDataSource,
  ) {
    super(MyUser, dataSource);
  }
}
