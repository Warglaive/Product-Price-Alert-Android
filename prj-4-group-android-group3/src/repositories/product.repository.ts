import {inject} from '@loopback/core';
import {DefaultCrudRepository} from '@loopback/repository';
import {MongoDataSource} from '../datasources';
import {Product, ProductRelations} from '../models';

export class ProductRepository extends DefaultCrudRepository<
  Product,
  typeof Product.prototype.id,
  ProductRelations
> {
  constructor(
    @inject('datasources.mongo') dataSource: MongoDataSource,
  ) {
    super(Product, dataSource);
  }

  findBySearchTerm(searchTerm: string) : Product | PromiseLike<Product> {
    const product = this.find({
      where: {
        name : searchTerm 
      }
    }) as unknown as Product
    return product;
  }
}
