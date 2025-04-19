import { ShortNumber } from './short-number.pipe';

describe('ShortNumberPipe', () => {
  it('create an instance', () => {
    const pipe = new ShortNumber();
    expect(pipe).toBeTruthy();
  });
});
