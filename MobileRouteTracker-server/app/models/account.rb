class Account < ActiveRecord::Base
  has_many :positions
end
