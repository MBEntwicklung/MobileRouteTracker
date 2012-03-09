class CreateAccounts < ActiveRecord::Migration
  def change
    create_table :accounts do |t|
      t.integer :accountnr
      t.integer :pass
      t.string :mail
      t.boolean :active

      t.timestamps
    end
  end
end
