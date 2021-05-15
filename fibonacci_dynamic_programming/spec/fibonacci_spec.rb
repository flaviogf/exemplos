require 'fibonacci'

RSpec.describe :fibonacci do
  it 'should return 0 when pass 0' do
    expect(fibonacci(0)).to eq(0)
  end

  it 'should return 1 when pass 1' do
    expect(fibonacci(1)).to eq(1)
  end

  it 'should return 3 when pass 4' do
    expect(fibonacci(4)).to eq(3)
  end

  it 'should return 6765 when pass 20' do
    expect(fibonacci(20)).to eq(6765)
  end
end
