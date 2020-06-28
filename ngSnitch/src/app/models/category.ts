import { Snitch } from './snitch';
import { Alert } from './alert';

export class Category {
  id: number;
  name: string;
  description: string;
  snitches: Snitch[];
  alerts: Alert[];

  constructor(
    id?: number,
    name?: string,
    description?: string,
    snitches?: Snitch[],
    alerts?: Alert[]
 ) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.snitches = snitches;
      this.alerts = alerts;

  }
}
