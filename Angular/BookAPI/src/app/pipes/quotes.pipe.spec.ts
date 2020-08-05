import { QuotesPipe } from './quotes.pipe';

describe('QuotesPipe', () => {
  it('create an instance', () => {
    const pipe = new QuotesPipe();
    expect(pipe).toBeTruthy();
  });
});
