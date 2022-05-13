import { inject } from '@loopback/core';
import { DefaultCrudRepository } from '@loopback/repository';
import { MongoDataSource } from '../datasources';
import { User, UserRelations } from '../models';

export class UserRepository extends DefaultCrudRepository<User, typeof User.prototype.id, UserRelations> {
  constructor(
    @inject('datasources.mongo') dataSource: MongoDataSource,
  ) {
    super(User, dataSource);
  }
  //custom login 
  login(email: string): User | PromiseLike<User> {
    const user = this.findOne({
      where: {
        email: email
      }
    }) as unknown as User
    return user;
  }
}
