import { Pipe, PipeTransform } from '@angular/core';
import { Snitch } from '../models/snitch';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { SnitchService } from '../services/snitch.service';

@Pipe({
  name: 'snitchCount'
})
export class SnitchCountPipe implements PipeTransform {

transform(snitches: Snitch[]): Snitch[] {
  const results = [];

  for (let i = 0; i < snitches.length; i++) {
    if (snitches[i].resolved === false){
      results.push(snitches[i]);
    }
  }
  return results;
}
}


// @Pipe({
//   name: 'personalSnitchCount'
// })
// export class PersonalSnitchCountPipe implements PipeTransform {

// transform(persSnitches: Snitch[]): Snitch[] {
//   let results = [];

//   for (let i = 0; i < snitches.length; i++) {
//     if ( && persSnitches[i].resolved === false){
//       results.push(persSnitches[i]);
//     }
//   }
//   return results;
