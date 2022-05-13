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
  //Find by Email custom method
  login(email: string, password: string): User | PromiseLike<User> {
    const user = this.findOne({
      where: {
        email: email,
        password: password
      }
    }) as unknown as User
    return user;
  }
}
