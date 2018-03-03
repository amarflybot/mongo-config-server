/**
 * Created by amarendra on 05/12/17.
 */

export interface Person {
  id: number;
  name: string;
  address: {
    streetName: string,
    country: string
  };
}
