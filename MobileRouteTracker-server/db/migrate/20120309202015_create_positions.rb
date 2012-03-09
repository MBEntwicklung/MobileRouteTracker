class CreatePositions < ActiveRecord::Migration
  def change
    create_table :positions do |t|
      t.references :account
      t.string :lat
      t.string :lng
      t.datetime :created
      t.text :description

      t.timestamps
    end
    add_index :positions, :account_id
  end
end
