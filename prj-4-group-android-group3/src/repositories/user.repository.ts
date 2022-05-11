import { inject } from '@loopback/core';
import { DefaultCrudRepository } from '@loopback/repository';
import { use } from 'should';
import { MongoDataSource } from '../datasources';
import { User, UserRelations } from '../models';

export class UserRepository extends DefaultCrudRepository< User,  typeof User.prototype.id,  UserRelations> {
  constructor(
    @inject('datasources.mongo') dataSource: MongoDataSource,
  ) {
    super(User, dataSource);
  }
  //Find by Email custom method
  findByEmail(email: string): User | PromiseLike<User> {
    const user = this.findOne({
      where: {
        email: email
      }
    }) as unknown as User
    return user;
  }
}
