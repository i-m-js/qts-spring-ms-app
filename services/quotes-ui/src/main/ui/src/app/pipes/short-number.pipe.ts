import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shortNumber'
})
export class ShortNumber implements PipeTransform {

  transform(value: string | number | undefined | null, ...args: unknown[]): string {
    if (value == null) {
      return '';
    } else if (typeof (value) !== 'number') {
      value = +value;
      value = Number.isNaN(value) ? 0 : value;
    }

    if (value >= 1_000_000_000) {
      return (value / 1_000_000_000).toFixed(1) + 'B';
    } else if (value >= 1_000_000) {
      return (value / 1_000_000).toFixed(0) + 'M';
    } else if (value > 1000) {
      return Math.floor(value / 1_000) + 'K';
    }

    return value.toString();
  }

}
