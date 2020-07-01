import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'snitchCount'
})
export class SnitchCountPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
